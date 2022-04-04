import os, json

###################################
"""
Edit here. COLOR will be replaced with an item of each 16 colors.
white colored block models will not be made, as it will be treated
as the base the other colors will parent from.
"""
namespace = "another_furniture"
generate_pre = [
    {"name": "oak_chair", "blockstate_preset": "4_way"},
    {"name": "oak_shelf", "blockstate_preset": "4_way"},
    {"name": "COLOR_stool", "colored": True}
]

def class_names(item_name):
    class_type = "CLASS"
    if item_name.endswith("chair"):
        class_type = "ChairBlock"
    elif item_name.endswith("shelf"):
        class_type = "ShelfBlock"
    elif item_name.endswith("stool"):
        class_type = "StoolBlock"
    return class_type

###################################
colors = [
    "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray",
    "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
]

def make_file_if_not_exist(path, json_dict):
    if not os.path.exists(path):
        with open(path, "w+") as f:
            f.write(json.dumps(json_dict, indent=4))


current_dir = os.getcwd()

generate = []
for item in generate_pre:
    item2 = {**{"colored": False, "blockstate_preset": "normal"}, **item}
    item_name1 = item2["name"]
    if "colored" in item2 and item2["colored"]:
        for color in colors:
            dct = {**item2, **{"name": item_name1.replace("COLOR", color)}}
            generate.append(dct)
            
    else:
        dct = {**item2, **{"name": item_name1}}
        generate.append(dct)
    
print(generate)

lang = {}
namespaced_items = []
for item in generate:
    #print(item)
    item_name = item["name"]
    item_color = item_name.split("_")[0]
    type_of_item = item_name.split("_")[1]

    namespaced_item = namespace + ":" + item_name
    #########################
    #Block Loot Tables
    block_loot_tables = f"{current_dir}\\data\\{namespace}\\loot_tables\\blocks"
    make_file_if_not_exist(f"{block_loot_tables}\\{item_name}.json",
        {
            "type": "minecraft:block",
            "pools": [
		{
		    "rolls": 1,
		    "entries": [
			{
			    "type": "minecraft:item",
			    "name": namespaced_item
			}
		    ],
		    "conditions": [
			{
			    "condition": "minecraft:survives_explosion"
			}
		    ]
		}
            ]
        }
    )
    #########################
    # Block Models
    block_model = f"{current_dir}\\assets\\{namespace}\\models\\block"
    if item["colored"]:
        if not item_name.startswith("white"):
            make_file_if_not_exist(f"{block_model}\\{item_name}.json",
                {
                    "parent": f"{namespace}:block/white_{type_of_item}",
                    "textures": {
                        "all": f"{namespace}:block/{type_of_item}/{item_color}",
                        "particle": "minecraft:block/oak_planks"
                    }
                }
            )
    #########################
    #Item Models
    item_model = f"{current_dir}\\assets\\{namespace}\\models\\item"
    make_file_if_not_exist(f"{item_model}\\{item_name}.json",
        {
            "parent": f"{namespace}:block/{item_name}"
        }
    )
    #########################
    #Blockstates
    blockstates = f"{current_dir}\\assets\\{namespace}\\blockstates"
    if item["blockstate_preset"] == "normal":
        blockstate = {
            "variants": {
		"": {
		    "model": f"{namespace}:block/{item_name}"
		}
            }
        }
    elif item["blockstate_preset"] == "4_way":
        blockstate = {
            "variants": {
		"facing=north": {
		    "model": f"{namespace}:block/{item_name}"
		},
		"facing=east": {
		    "model": f"{namespace}:block/{item_name}",
		    "y": 90
		},
		"facing=south": {
		    "model": f"{namespace}:block/{item_name}",
		    "y": 180
		},
		"facing=west": {
		    "model": f"{namespace}:block/{item_name}",
		    "y": 270
		}
            }
        }

    make_file_if_not_exist(f"{blockstates}\\{item_name}.json", blockstate)
    
    
    #########################
    #Code
    class_type = class_names(item_name)
    
    print('public static final RegistryObject<Block> UPPERCASE_ID = registerBlock(LOWERCASE_ID, () -> new CLASS(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));'.replace("UPPERCASE_ID", item_name.upper()).replace("LOWERCASE_ID", f'"{item_name.lower()}"').replace("CLASS", class_type))

    #########################
    #Ids List
    namespaced_items.append(namespaced_item)
    #########################
    #Lang
    lang[f"itemGroup.{namespace}"] = "Another Furniture Mod"
    lang[f"block.{namespace}.{item_name}"] = item_name.replace("_", " ").title()
    
langs = f"{current_dir}\\assets\\{namespace}\\lang\\en_us.json"
make_file_if_not_exist(langs, lang)

for x in namespaced_items:
    print(f'"{x}",')
#########################





