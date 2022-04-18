import os, json

###################################
"""
Edit here. COLOR will be replaced with an item of each 16 colors.
white colored block models will not be made, as it will be treated
as the base the other colors will parent from.
"""
namespace = "another_furniture"
generate_pre = [
    {"name": "WOODTYPE_chair", "wood_types": True, "blockstate_preset": "4_way", "tags": [f"{namespace}:blocks/chairs", {"minecraft:blocks/mineable/axe": f"{namespace}:blocks/chairs"}, {"minecraft:blocks/unstable_bottom_center": f"{namespace}:blocks/chairs"}], "recipe": {"type":"minecraft:crafting_shaped","pattern":["# ","##","//"],"key":{"#":{"item":"minecraft:WOODTYPE_planks"},"/":{"item":"minecraft:stick"}},"result":{"item":"another_furniture:WOODTYPE_chair","count":1},"group":"chairs"}},
    {"name": "WOODTYPE_shelf", "wood_types": True, "blockstate_preset": "shelf", "tags": [f"{namespace}:blocks/shelves", {"minecraft:blocks/mineable/axe": f"{namespace}:blocks/shelves"}], "recipe": {"type":"minecraft:crafting_shaped","pattern":["###","/  "],"key":{"#":{"item":"minecraft:WOODTYPE_planks"},"/":{"item":"minecraft:stick"}},"result":{"item":"another_furniture:WOODTYPE_shelf","count":1},"group":"shelves"}},
    {"name": "WOODTYPE_table", "wood_types": True, "blockstate_preset": "table", "tags": [f"{namespace}:blocks/tables", {"minecraft:blocks/mineable/axe": f"{namespace}:blocks/tables"}], "recipe": {"type":"minecraft:crafting_shaped","pattern":["###","/ /"],"key":{"#":{"item":"minecraft:WOODTYPE_planks"},"/":{"item":"minecraft:stick"}},"result":{"item":"another_furniture:WOODTYPE_table","count":1},"group":"tables"}},
    {"name": "COLOR_stool", "colored": True, "tags": [f"{namespace}:blocks/stools", {"minecraft:blocks/mineable/axe": f"{namespace}:blocks/stools"}, {"minecraft:blocks/unstable_bottom_center": f"{namespace}:blocks/stools"}], "recipe": {"type":"minecraft:crafting_shaped","pattern":["#W#","/ /"],"key":{"#":{"tag":"minecraft:planks"},"W":{"item":"minecraft:COLOR_wool"},"/":{"item":"minecraft:stick"}},"result":{"item":"another_furniture:COLOR_stool","count":1},"group":"stools"}}
]


def class_names(item_name):
    class_type = "CLASS"
    if item_name.endswith("chair"):
        class_type = "ChairBlock"
    elif item_name.endswith("shelf"):
        class_type = "ShelfBlock"
    elif item_name.endswith("stool"):
        class_type = "StoolBlock"
    elif item_name.endswith("table"):
        class_type = "TableBlock"
    return class_type

def material_names(item_name):
    material_type = "WOOD"
    for wood_type in wood_types:
        if wood_type in item_name:
            material_type = "WOOD"
            for non_flammable_wood in non_flammable_woods:
                if non_flammable_wood in item_name:
                    return "NETHER_WOOD"
                
    if item_name.endswith("stool"):
        material_type = "WOOD"
    return material_type

###################################
colors = [
    "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray",
    "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
]

wood_types = [
    "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "crimson", "warped"
]

non_flammable_woods = [
    "crimson", "warped"
]

def make_file_if_not_exist(path, json_dict):
    if not os.path.exists(path):
        with open(path, "w+") as f:
            f.write(json.dumps(json_dict, indent=4))


current_dir = os.getcwd()
block_loot_tables = f"{current_dir}\\src\\main\\resources\\data\\{namespace}\\loot_tables\\blocks"
recipes = f"{current_dir}\\src\\main\\resources\\data\\{namespace}\\recipes"
block_model = f"{current_dir}\\src\\main\\resources\\assets\\{namespace}\\models\\block"
item_model = f"{current_dir}\\src\\main\\resources\\assets\\{namespace}\\models\\item"
blockstates = f"{current_dir}\\src\\main\\resources\\assets\\{namespace}\\blockstates"
langs = f"{current_dir}\\src\\main\\resources\\assets\\{namespace}\\lang\\en_us.json"

def generate_list(generate_pre):
    generate = []
    for item in generate_pre:
        item2 = {**{"colored": False, "wood_types": False, "blockstate_preset": "normal", "tags": [], "recipe": {}}, **item}
        item_name1 = item2["name"]
        if "colored" in item2 and item2["colored"]:
            for color in colors:
                dct = {**item2, **{"name": item_name1.replace("COLOR", color)}, **{"color": color}}
                generate.append(dct)
                
        elif "wood_types" in item2 and item2["wood_types"]:
            for wood_type in wood_types:
                dct = {**item2, **{"name": item_name1.replace("WOODTYPE", wood_type), **{"wood_type": wood_type}}}
                generate.append(dct)
                
        else:
            dct = {**item2, **{"name": item_name1}}
            generate.append(dct)
        item2 = {}
    return generate


tags_dict = {}

def generate_all(generate):
    lang = {}
    namespaced_items = []
    for item in generate:
        item_name = item["name"]
        type_of_item = item_name.split("_")[-1]

        namespaced_item = namespace + ":" + item_name
        #########################
        #Block Loot Tables
        
        make_file_if_not_exist(f"{block_loot_tables}\\{item_name}.json",
            {"type":"minecraft:block","pools":[{"rolls":1,"entries":[{"type":"minecraft:item","name":namespaced_item}],"conditions":[{"condition":"minecraft:survives_explosion"}]}]}
        )
        #########################
        # Block Models
        
        if item["colored"]:
            make_file_if_not_exist(f"{block_model}\\{item_name}.json",
                {"parent":f"{namespace}:block/template/{type_of_item}","textures":{"all":f"{namespace}:block/{type_of_item}/{item['color']}","particle":f"minecraft:block/{item['color']}_wool"}}
            )
        elif item["wood_types"]:
            if item["blockstate_preset"] == "table":
                make_file_if_not_exist(f"{block_model}\\table\\{item['wood_type']}_leg.json",
                    {"parent": f"{namespace}:block/template/table_leg","textures":{"all":f"{namespace}:block/table/{item['wood_type']}","particle":f"minecraft:block/{item['wood_type']}_planks"}}
                )
                make_file_if_not_exist(f"{block_model}\\table\\{item['wood_type']}_top.json",
                    {"parent": f"{namespace}:block/template/table_top","textures":{"all":f"{namespace}:block/table/{item['wood_type']}","particle":f"minecraft:block/{item['wood_type']}_planks"}}
                )
            elif item["blockstate_preset"] == "shelf":
                make_file_if_not_exist(f"{block_model}\\shelf\\{item['wood_type']}_top.json",
                    {"parent": f"{namespace}:block/template/shelf_top","textures":{"all":f"{namespace}:block/shelf/{item['wood_type']}","particle":f"minecraft:block/{item['wood_type']}_planks"}}
                )
                make_file_if_not_exist(f"{block_model}\\shelf\\{item['wood_type']}_l.json",
                    {"parent": f"{namespace}:block/template/shelf_l","textures": {"all": f"{namespace}:block/shelf/{item['wood_type']}","particle":f"minecraft:block/{item['wood_type']}_planks"}}
                )
                make_file_if_not_exist(f"{block_model}\\shelf\\{item['wood_type']}_r.json",
                    {"parent": f"{namespace}:block/template/shelf_r","textures": {"all": f"{namespace}:block/shelf/{item['wood_type']}","particle":f"minecraft:block/{item['wood_type']}_planks"}}
                )
            else:
                make_file_if_not_exist(f"{block_model}\\{item_name}.json",
                    {"parent": f"{namespace}:block/template/{type_of_item}","textures":{"all":f"{namespace}:block/{type_of_item}/{item['wood_type']}","particle": f"minecraft:block/{item['wood_type']}_planks"}}
                )
        #########################
        #Item Models
        if item["blockstate_preset"] == "table":
            make_file_if_not_exist(f"{item_model}\\{item_name}.json",
                {"parent": f"{namespace}:block/template/table_full", "textures": {"all": f"{namespace}:block/table/{item['wood_type']}"}}
            )
        elif item["blockstate_preset"] == "shelf":
            make_file_if_not_exist(f"{item_model}\\{item_name}.json",
                {"parent": f"{namespace}:block/template/shelf_full", "textures": {"all": f"{namespace}:block/shelf/{item['wood_type']}"}}
            )
        else:
            make_file_if_not_exist(f"{item_model}\\{item_name}.json",
                {"parent": f"{namespace}:block/{item_name}"}
            )
        #########################
        #Blockstates
        
        if item["blockstate_preset"] == "normal":
            blockstate = {"variants": {"": {"model": f"{namespace}:block/{item_name}"}}}
        elif item["blockstate_preset"] == "4_way":
            blockstate = {"variants": {"facing=north": {"model": f"{namespace}:block/{item_name}"},"facing=east": {"model": f"{namespace}:block/{item_name}","y": 90},"facing=south": {"model": f"{namespace}:block/{item_name}","y": 180},"facing=west": {"model": f"{namespace}:block/{item_name}","y": 270}}}
        elif item["blockstate_preset"] == "shelf":
            blockstate = {"multipart":[{"when":{"facing":"north"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_top"}},{"when":{"facing":"east"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_top","y":90}},{"when":{"facing":"south"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_top","y":180}},{"when":{"facing":"west"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_top","y":270}},{"when":{"facing":"north","type":"0|1"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_l"}},{"when":{"facing":"east","type":"0|1"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_l","y":90}},{"when":{"facing":"south","type":"0|1"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_l","y":180}},{"when":{"facing":"west","type":"0|1"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_l","y":270}},{"when":{"facing":"north","type":"0|3"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_r"}},{"when":{"facing":"east","type":"0|3"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_r","y":90}},{"when":{"facing":"south","type":"0|3"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_r","y":180}},{"when":{"facing":"west","type":"0|3"},"apply":{"model":f"{namespace}:block/shelf/{item['wood_type']}_r","y":270}}]}
        elif item["blockstate_preset"] == "table":
            blockstate = {"multipart":[{"when":{"facing":"north"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_top"}},{"when":{"facing":"east"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_top","y":90}},{"when":{"facing":"south"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_top","y":180}},{"when":{"facing":"west"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_top","y":270}},{"when":{"leg_1":"true"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_leg","uvlock":True}},{"when":{"leg_2":"true"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_leg","y":90,"uvlock":True}},{"when":{"leg_3":"true"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_leg","y":180,"uvlock":True}},{"when":{"leg_4":"true"},"apply":{"model":f"{namespace}:block/table/{item['wood_type']}_leg","y":270,"uvlock":True}}]}
        make_file_if_not_exist(f"{blockstates}\\{item_name}.json", blockstate)
        


        #########################
        #Recipes

        if not len(item["recipe"]) == 0:
            if item["colored"]:
                key = "W"
                data = item["color"]
                extra = "wool"
                
            elif item["wood_types"]:
                key = "#"
                data = item["wood_type"]
                extra = "planks"
                
            recipe = item["recipe"]
            key_namespace = recipe["key"][key]["item"].split(":")[0]
            recipe["key"][key]["item"] = f"{key_namespace}:{data}_{extra}"
            recipe["result"]["item"] = f"{namespace}:{item_name}"
            make_file_if_not_exist(f"{recipes}\\{item_name}.json", recipe)

        if item_name.endswith("stool"):
            if not item["color"] == "white":
                make_file_if_not_exist(f"{recipes}\\{item_name}_dyeing.json", {"type": "minecraft:crafting_shapeless","ingredients": [{"item": f"{namespace}:white_{type_of_item}"},{"item": f"minecraft:{item['color']}_dye"}],"result": {"item": f"{namespace}:{item_name}","count": 1},"group": "stools"})
        #####
        #Compat - Corail Woodcutter
        if item["wood_types"]:
            make_file_if_not_exist(f"{recipes}\\compat\\corail_woodcutter\\{item_name}_from_plank.json",{"type":"corail_woodcutter:woodcutting","conditions":[{"type":"forge:mod_loaded","modid":"corail_woodcutter"}],"ingredient":{"item":f"minecraft:{item['wood_type']}_planks"},"result": f"{namespace}:{item_name}","count": 1})
            make_file_if_not_exist(f"{recipes}\\compat\\corail_woodcutter\\{item_name}_from_log.json",{"type":"corail_woodcutter:woodcutting","conditions":[{"type":"forge:mod_loaded","modid":"corail_woodcutter"}],"ingredient":{"tag":f"minecraft:{item['wood_type']}_logs"},"result": f"{namespace}:{item_name}","count": 4})
        #########################
        #Tags #1

        for tag in item["tags"]:
            if type(tag) == str:
                if not tag in tags_dict:
                    tags_dict[tag] = []
                tags_dict[tag].append(namespaced_item)
                
                if item["wood_types"] and (item["wood_type"] in non_flammable_woods):
                    if not "minecraft:blocks/non_flammable_wood" in tags_dict:
                        tags_dict["minecraft:blocks/non_flammable_wood"] = []
                    tags_dict["minecraft:blocks/non_flammable_wood"].append(namespaced_item)
            elif type(tag) == dict:
                dict_key = str(list(tag.keys())[0])
                dict_value = "#" + str(list(tag.values())[0]).replace("blocks/","")
                
                if not dict_key in tags_dict:
                    tags_dict[dict_key] = []
                    
                if not dict_value in tags_dict[dict_key]:
                    tags_dict[dict_key].append(dict_value)
        
        #########################
        #Code
        class_type = class_names(item_name)
        material_name = material_names(item_name)
        
        print(f'public static final RegistryObject<Block> {item_name.upper()} = registerBlock("{item_name.lower()}",\n() -> new {class_type}(Block.Properties.of(Material.{material_name}).strength(2.0F, 3.0F).sound(SoundType.WOOD)));')

        
        #########################
        #Lang
        lang[f"itemGroup.{namespace}"] = "Another Furniture Mod"
        lang[f"block.{namespace}.{item_name}"] = item_name.replace("_", " ").title()
        #########################
        namespaced_items.append(namespaced_item)
        
    make_file_if_not_exist(langs, lang)

    #########################
    #Ids List
    #
    #for x in namespaced_items:
    #    print(f'"{x}",')
    
generate = generate_list(generate_pre)
generate_all(generate)


#########################
#Tags #2

for tag in tags_dict:
    block_tags = f"{current_dir}\\data\\{tag.replace(':', '/tags/')}.json"
    make_file_if_not_exist(block_tags, {"replace": False, "values": tags_dict[tag]})




