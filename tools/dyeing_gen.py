import json

#################################################################

def getPlural(name: str):
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


colors = [
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


furniture_type = input("white_")
group = getPlural(furniture_type)

for color in colors:
    dye = f"minecraft:{color}_dye"
    ingredient = f"another_furniture:white_{furniture_type}"
    result = f"another_furniture:{color}_{furniture_type}"
    
    jsn= {
        "type": "minecraft:crafting_shapeless",
        "group": group,
        "ingredients": [
            {
                "item": dye
            },
            {
                "item": ingredient
            }
        ],
        "result": {
            "item": result
        }
    }

##    jsn = {
##  "parent": "minecraft:recipes/root",
##  "criteria": {
##    "has_the_recipe": {
##      "conditions": {
##        "recipe": f"another_furniture:{color}_lamp_dyeing"
##      },
##      "trigger": "minecraft:recipe_unlocked"
##    },
##    "has_white_lamp": {
##      "conditions": {
##        "items": [
##          {
##            "items": [
##              "another_furniture:white_lamp"
##            ]
##          }
##        ]
##      },
##      "trigger": "minecraft:inventory_changed"
##    }
##  },
##  "requirements": [
##    [
##      "has_white_lamp",
##      "has_the_recipe"
##    ]
##  ],
##  "rewards": {
##    "recipes": [
##      f"another_furniture:{color}_lamp_dyeing"
##    ]
##  }
##}
    filename = f"{color}_{furniture_type}_dyeing.json"
    print(jsn)
    with open(filename, "w+") as f:
        f.write(json.dumps(jsn, indent=4) + "\n")
