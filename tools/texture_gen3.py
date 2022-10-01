from PIL import Image
import json
import os

"""
Get the color palette that we want to replace.
Searches for unique colors and adds them to a list.
"""
def get_unique_pixels(path):
    img = Image.open(path).convert(mode="RGBA",dither=Image.Dither.NONE)
    img_loaded = img.load()

    palette_data = []
    for y in range(img.size[0]):
        for x in range(img.size[1]):
            for palette_data_entry in palette_data:
                if palette_data_entry["color"] == img_loaded[x,y] or img_loaded[x,y][3] == 0:
                    break
            else:
                palette_data.append({"x":x,"y":y,"color":img_loaded[x,y]})

    return palette_data



with open("texture_gen3.json") as f:
    config = json.loads(f.read())

print("palettes: " + str(list(config["palettes"].keys())).replace("'",""))
use_palette = input("Use palette: ")

extension = config["extension"]
using = config["palettes"][use_palette]
primary_palette = get_unique_pixels(using["primary_palette_path"] + extension)

def generate_types():
    wood_types = ["oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "crimson", "warped"]

    

    planks = [os.path.join(using["secondary_palette"]["folder"], file) for file in using["secondary_palette"]["files"]]
    templates = [os.path.join(using["template_path"]["folder"], file) for file in using["template_path"]["files"]]
    
    primary_palette_data = get_palette_data(plank_path, "oak_planks")
    
    for plank in planks:
        plank_nopng = plank.replace(".png", "")
        palette_noload = Image.open(plank).convert("RGBA",dither=Image.Dither.NONE,colors=7)
        palette = palette_noload.load()
        palette_size = palette_noload.size
        
        secondary_palette_data = []
        print(plank)
        for primary_palette_data_item in primary_palette_data:
            x = primary_palette_data_item["x"]
            y = primary_palette_data_item["y"]
            secondary_palette_data.append({"x":x,"y":y,"color":palette[x,y]})
        print(secondary_palette_data)

        for template in templates:
            is_valid_template = True
            for banned_variant in banned_variants:
                if banned_variant in template:
                    is_valid_template = False
                    
            if is_valid_template:
                input_img = Image.open(template).convert(mode="RGBA",dither=Image.Dither.NONE)
                input_img_loaded = input_img.load()

                # skip swapping colors if it's oak
                if not plank.split("\\")[-1].startswith("oak"):
                    input_img_size = input_img.size
                    img_new = input_img
                    image_new = img_new.load()
                    
                    size = input_img.size
                    for y in range(size[0]):
                        for x in range(size[1]):

                            
                            pix = input_img_loaded[x,y]
                            for num, primary_palette_data_item in enumerate(primary_palette_data):
                                if pix == primary_palette_data_item["color"]:
                                    image_new[x,y] = secondary_palette_data[num]["color"]
                                elif pix == (0, 0, 0, 0):
                                    image_new[x,y] = (0, 0, 0, 0)
                else:
                    img_new = input_img
                    
                save_path = template.replace("oak", plank.split("\\")[-1].replace(".png","")).replace("_planks","")
                img_new.save(save_path)
                print(f"saved {save_path}")
