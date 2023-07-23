from pathlib import Path
import os
import json

def load_properties(filepath, sep='=', comment_char='#'):
    """
    Read the file passed as parameter as a properties file.
    """
    properties = {}
    with open(filepath, "rt") as f:
        for line in f:
            l = line.strip()
            if l and not l.startswith(comment_char):
                key_value = l.split(sep)
                key = key_value[0].strip()
                value = sep.join(key_value[1:]).strip().strip('"') 
                properties[key] = value 
    return properties

def get_plural(name: str):
    addition = {"ch": "es","sh": "es","x": "es","s": "es","z": "es"}
    replace = {"f": "ves"}
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

def is_version_greater_or_equal(target: str):
    _, version_on_major, version_on_minor = minecraft_version.split(".")
    _, target_major, target_minor = target.split(".")

    version_on_major = int(version_on_major)
    version_on_minor = int(version_on_minor)

    target_major = int(target_major)
    target_minor = int(target_minor)
    
    if version_on_major > target_major:
        return True
    if version_on_major == target_major and version_on_minor >= target_minor:
        return True
    return False
    
def can_use_planks_in_version(plank: str):
    if plank not in planks:
        raise ValueError(f"value '{plank}' does not exist in planks variable")
    if plank == "mangrove":
        return is_version_greater_or_equal("1.19.0")
    if plank in ["bamboo", "cherry"]:
        return is_version_greater_or_equal("1.20.0")
    return True

def can_use_furniture_in_version(furniture: str):
    if furniture not in furnitures:
        raise ValueError(f"value '{furniture}' does not exist in furnitures variable")
    if furniture in ["lattice", "awning", "grandfather_clock"]:
        return is_version_greater_or_equal("1.19.2")
    return True

def get_versioned_list(iterables: list):
    versioned = []
    for item in iterables:
        if item in planks and can_use_planks_in_version(item):
            versioned.append(item)
        if item in furnitures and can_use_furniture_in_version(item):
            versioned.append(item)
    return versioned

def get_id(namespace = "minecraft", name: str = None):
    if name == None:
        raise ValueError("2nd argument cannot be empty")
    if ":" in name:
        return name
    return namespace + ":" + name

def save_json(path, json_):
    path = str(path)
    if not path.endswith(".json"):
        path += ".json"
    
    with open(path, "w+") as f:
        if type(json_) == dict:
            f.write(json.dumps(json_, indent=4) + "\n")
        else:
            f.write(json_)

wooden_furnitures = [
    "chair",
    "shelf",
    "table",
    "shutter",
    "bench",
    "drawer",
    "lattice",
    "planter_box",
    "grandfather_clock"
]

colored_furnitures = [
    "stool",
    "curtain",
    "lamp",
    "sofa",
    "tall_stool",
    "awning"
]

misc_furnitures = [
    "service_bell"
]

furnitures = wooden_furnitures + colored_furnitures + misc_furnitures

colors = [
    "white",
    "orange",
    "magenta",
    "light_blue",
    "yellow",
    "lime",
    "pink",
    "gray",
    "light_gray",
    "cyan",
    "purple",
    "blue",
    "brown",
    "green",
    "red",
    "black"
]

planks = [
    "oak",
    "spruce",
    "birch",
    "jungle",
    "acacia",
    "dark_oak",
    "crimson",
    "warped",
    "mangrove",
    "bamboo",
    "cherry"
]

current_dir = Path(os.getcwd())
project_dir = current_dir.parent.absolute()

properties = load_properties(f"{project_dir}\\gradle.properties")

namespace = properties["archives_base_name"]
minecraft_version = properties["minecraft_version"]

data_dir = project_dir / "common\\src\\main\\resources\\data"
tag_blocks_non_flammable_wood = data_dir / "minecraft\\tags\\blocks\\non_flammable_wood.json"
tag_items_non_flammable_wood = data_dir / "minecraft\\tags\\items\\non_flammable_wood.json"
tag_mineable_axe = data_dir / "minecraft\\tags\\blocks\\mineable\\axe.json"
tag_mineable_pickaxe = data_dir / "minecraft\\tags\\blocks\\mineable\\pickaxe.json"
tag_mod_blocks = data_dir / namespace / "tags\\blocks"
tag_mod_items = data_dir / namespace / "tags\\items"
