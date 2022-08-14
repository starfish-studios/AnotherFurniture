import jstyleson as json
import os

# why did i even write this as a class
class Variants:
    def __init__(self, name: str, variants: list, conditions: dict = {"version":"1.19"}):
        self.name = name
        self.variants = self._checkConditions(variants, conditions)
        self.current_index = 0

    def _checkConditions(self, variants, conditions):
        processed_variants = []
        for variant in variants:
            if type(variant) == str:
                processed_variants.append(variant)
            elif type(variant) == dict:
                for condition in variant["conditions"]:
                    passed = True
                    if condition not in conditions or not variant["conditions"][condition] == conditions[condition]:
                        passed = False
                if passed:
                    processed_variants.append(variant["variant"])
            else:
                raise ValueError(f"type {variant} is not str or dict type.")
        return processed_variants
    
    def getCurrent(self):
        return self.variants[self.current_index]
    
    def getNext(self):
        self.current_index += 1
        if self.current_index > len(self.variants) - 1:
            self.current_index = 0
        return self.variants[self.current_index]

    def getAll(self):
        return self.variants

#################################################################

def ensure_outputs_exist():
    for file in os.walk(_cwd + f"\\{input_path}\\"):
        try:
            os.mkdir(file[0].replace(input_path, output_path))
        except:
            continue

# these 2 are for looping/replacing through every single key and value in a dict
def dict_replace_value(d, old, new):
    x = {}
    for k, v in d.items():
        if isinstance(v, dict):
            v = dict_replace_value(v, old, new)
        elif isinstance(v, list):
            v = list_replace_value(v, old, new)
        elif isinstance(v, str):
            if not old is None and not new is None:
                v = v.replace(old, new)
            v = v.replace("{namespace}",f"{namespace}")
        x[k] = v
    return x

def list_replace_value(l, old, new):
    x = []
    for e in l:
        if isinstance(e, list):
            e = list_replace_value(e, old, new)
        elif isinstance(e, dict):
            e = dict_replace_value(e, old, new)
        elif isinstance(e, str):
            if not old is None and not new is None:
                e = e.replace(old, new)
            e = e.replace("{namespace}",f"{namespace}")
        x.append(e)
    return x

#################################################################


def getMineableType(item: dict):
    if "material" in item:
        material_type = item["material"]
        if material_type in ["WOOD"]:
            return "axe"
        elif material_type in ["STONE", "METAL", "LANTERN"]:
            return "pickaxe"
    return None 

def getClassType(item: dict):
    class_type = item["type"].replace("_"," ").title().replace(" ", "") + "Block"
    return class_type

def getSoundType(item: dict):
    sound_type = "STONE"
    if "sound" in item:
        sound_type = item["sound"]
    return sound_type

def getMaterialType(item: dict, variant: str = None):
    material_type = "STONE"
    if "material" in item:
        material_type = item["material"]
        if material_type == "WOOD" and "variant_type" in item and "non_flammable_wood" in variants and item["variant_type"] in variants["non_flammable_wood"]:
            material_type = "NETHER_WOOD"
    return material_type

def getStrength(item: dict):
    strength = item["strength"]
    destroy_time = 0.1
    explosion_resistance = 0.1
    if type(strength) == int or type(strength) == float:
        destroy_time = strength
        explosion_resistance = strength
    else:
        destroy_time = strength[0]
        explosion_resistance = strength[1]
    return destroy_time, explosion_resistance

def getItemName(item: dict, variant: str = None):
    if variant != None:
        item_name = f"{variant}_{item['type']}"
    else:
        item_name = item["type"]
    full_item_name = f"{namespace}:{item_name}"
    return item_name, full_item_name

def getItemPath(item: dict, variant: str = None, extension: str = ".json"):
    if variant != None:
        item_path = "{" + item["variant_type"] + "}_" + item["type"] + extension
    else:
        item_path = item["type"] + extension
    return item_path

addition = {"ch": "es","sh": "es","x": "es","s": "es","z": "es"}
replace = {"f": "ves"}
def tryGetPlural(name: str):
    for x in addition:
        if name.endswith(x):
            name += addition[x]
            return name

    for y in replace:
        b = name
        if name.endswith(y):
            b = replace[y].join(name.rsplit(y, 1))
            if name != b:
                return b
        
    name += "s"
    return name

#################################################################

def addInputLangsFromOutput(namespace: str = "minecraft"):
    path = f"{input_path}\\{namespace}\\assets\\{namespace}\\lang\\en_us.json"
    save_path = f"{output_path}\\{namespace}\\assets\\{namespace}\\lang\\en_us.json"
    if os.path.exists(path):
        f = open(path)
        langs = json.load(f)
        with open(save_path, "w+") as f:
            f.write(json.dumps(langs, indent=4) + "\n")

def getVariantList(namespace: str = "minecraft"):
    path = f"{input_path}\\{namespace}\\variant_types.json"
    f = open(path)
    variants = json.load(f)
    processed_variants = {}
    for variant_type in variants:
        processed_variants[variant_type] = Variants(variant_type,variants[variant_type]).getAll()
    return processed_variants

def getInputFiles():
    input_files = [os.path.join(path, name) for path, subdirs, files in os.walk(f"input\\{namespace}") for name in files if name.endswith(".json")]
    return input_files
#################################################################

def generateHardcodedTags(item: dict, namespace: str = "minecraft", variant: str = None):
    item_name, full_item_name = getItemName(item, variant)
    path_non_flammable_wood = f"{output_path}\\{namespace}\\data\\minecraft\\tags\\blocks\\non_flammable_wood.json"
    if "variant_type" in item and "non_flammable_wood" in variants and variant in variants["non_flammable_wood"]:
        if os.path.exists(path_non_flammable_wood):
            f = open(path_non_flammable_wood)
            tag = json.load(f)
        else:
            tag = {"values": []}
        tag["values"].append(full_item_name)
        with open(path_non_flammable_wood, "w+") as f:
            f.write(json.dumps(tag, indent=4) + "\n")
        if settings_print_saves:
            print(f"[Save][Tags][Blocks][non_flammable_wood] {full_item_name}")
            
    mineable_type = getMineableType(item) 
    if mineable_type != None:
        path_mineable_type = f"{output_path}\\{namespace}\\data\\minecraft\\tags\\blocks\\mineable\\{mineable_type}.json"
        if os.path.exists(path_mineable_type):
            f = open(path_mineable_type)
            tag = json.load(f)
        else:
            tag = {"values": []}
        tag["values"].append(full_item_name)
        with open(path_mineable_type, "w+") as f:
            f.write(json.dumps(tag, indent=4) + "\n")
        if settings_print_saves:
            print(f"[Save][Tags][Blocks][mineable/{mineable_type}] {full_item_name}")
    

def generateLang(item: dict, namespace: str = "minecraft", variant: str = None):
    item_name, full_item_name = getItemName(item, variant)
    save_path = f"{output_path}\\{namespace}\\assets\\{namespace}\\lang\\en_us.json"
    if os.path.exists(save_path):
        f = open(save_path)
        langs = json.load(f)
    else:
        langs = []
    lang = f"block.{namespace}.{item_name}"
    lang_string = item_name.replace("_", " ").title()
    langs[lang] = lang_string
    with open(save_path, "w+") as f:
        f.write(json.dumps(langs, indent=4) + "\n")
    if settings_print_saves:
        print(f"[Save][Lang][en_us] {lang} = {lang_string}")


def generateTag(item: dict, namespace: str = "minecraft", variant: str = None):
    if variant != None:
        item_name, full_item_name = getItemName(item, variant)
        plural_item_name = tryGetPlural(item["type"])
        save_path = f"{output_path}\\{namespace}\\data\\{namespace}\\tags\\blocks\\{plural_item_name}.json"
        if os.path.exists(save_path):
            f = open(save_path)
            tag = json.load(f)
        else:
            tag = {"values": []}
        tag["values"].append(full_item_name)
        with open(save_path, "w+") as f:
            f.write(json.dumps(tag, indent=4) + "\n")
        if settings_print_saves:
            print(f"[Save][Tags][Blocks][{plural_item_name}] {full_item_name}")
        save_path = f"{output_path}\\{namespace}\\data\\{namespace}\\tags\\items\\{plural_item_name}.json"
        if os.path.exists(save_path):
            f = open(save_path)
            tag = json.load(f)
        else:
            tag = {"values": []}
        tag["values"].append(full_item_name)
        with open(save_path, "w+") as f:
            f.write(json.dumps(tag, indent=4) + "\n")
        if settings_print_saves:
            print(f"[Save][Tags][Items][{plural_item_name}] {full_item_name}")



def generateDataForItem(item: dict, namespace: str = "minecraft", variant: str = None, version: str = ""):
    generateLang(namespace, variant, item)
    generateTag(namespace, variant, item)
    generateHardcodedTags(namespace, variant, item)


def generateDataForVariants():
    for input_file in input_files:
        input_json = input_file.split("\\")[-1]
        if "{" in input_json and "}" in input_json:
            for variant_type in variants:
                data = "{" + variant_type + "}"
                if data in input_json:
                    for variant in variants[variant_type]:
                        modified_json = dict_replace_value(json.load(open(input_file)), data, variant)
                        save_path = input_file.replace(f"{input_path}",f"{output_path}")
                        save_path = save_path.replace(data, variant)
                        with open(save_path, "w+") as f:
                            f.write(json.dumps(modified_json, indent=4) + "\n")
                        if settings_print_saves:
                            print(f"[Save][Dynamic][Variant] {save_path}")
        else:
            save_path = input_file.replace(f"{input_path}",f"{output_path}")
            with open(save_path, "w+") as f:
                f.write(json.dumps(json.load(open(input_file)), indent=4) + "\n")
                if settings_print_saves:
                    print(f"[Save][Dynamic][Non-Variant] {save_path}")
                    
    
def generateAllData():
    #if "versions" in variants and len(variants["versions"]) >= 1:
        
    f = open(f"input\\{namespace}\\registry.json")
    registry = json.load(f)
    block_item_registry = registry["block_item"]
    
    generateDataForVariants()
    for item in block_item_registry:
        if "variant_type" in item and item["variant_type"] in variants:
            for variant in variants[item["variant_type"]]:
                generateDataForItem(item, namespace, variant)
        else:
            generateDataForItem(item, namespace)
            
settings_print_saves = False

namespace = "another_furniture"
input_path = "input"
output_path = "output"

_cwd = os.getcwd()
try:
    [os.remove(os.path.join(path, name)) for path, subdirs, files in os.walk(f"{output_path}\\{namespace}") for name in files if name.endswith(".json")]
except:
    pass

ensure_outputs_exist()


input_files = getInputFiles()
addInputLangsFromOutput()
variants = getVariantList(namespace)
generateAllData()
