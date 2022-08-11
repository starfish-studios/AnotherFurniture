from PIL import Image
import os
def generate_wood_types(plank_path = "C:\\Users\\jacec\\Desktop\\planks", template_path = "C:\\Users\\jacec\\Desktop\\another_furniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block", banned_variants = ["chair", "shutter"]):
    planks = [os.path.join(dp, f) for dp, dn, filenames in os.walk(plank_path) for f in filenames if os.path.splitext(f)[1] == '.png']
    templates = [os.path.join(dp, f) for dp, dn, filenames in os.walk(template_path) for f in filenames if (os.path.splitext(f)[1] == '.png' and os.path.splitext(f)[0].startswith("oak_"))]
    oak_1 = (184, 148, 95, 255) # 0,  0
    oak_2 = (175, 143, 85, 255) # 1,  0
    oak_3 = (194, 157, 98, 255) # 3,  0
    oak_4 = (150, 116, 65, 255) # 15, 0
    oak_5 = (159, 132, 77, 255) # 15, 1
    oak_6 = (103, 80, 44, 255)  # 15, 3
    oak_7 = (126, 98, 55, 255)  # 14, 3
    
    for plank in planks:
        plank_nopng = plank.replace(".png", "")
        palette = Image.open(plank).convert("RGBA",dither=Image.Dither.NONE,colors=7).load()

        palette_1 = palette[0, 0]
        palette_2 = palette[1, 0]
        palette_3 = palette[3, 0]
        palette_4 = palette[15, 0]
        palette_5 = palette[15, 1]
        palette_6 = palette[15, 3]
        palette_7 = palette[14, 3]

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
                    img_new = Image.new("RGBA", (input_img_size[0], input_img_size[1]), color = "white")
                    image_new = img_new.load()
                    
                    size = input_img.size
                    for y in range(size[0]):
                        for x in range(size[1]):
                            pix = input_img_loaded[x,y]
                            
                            if pix == oak_1:
                                image_new[x,y] = palette_1
                            elif pix == oak_2:
                                image_new[x,y] = palette_2
                            elif pix == oak_3:
                                image_new[x,y] = palette_3
                            elif pix == oak_4:
                                image_new[x,y] = palette_4
                            elif pix == oak_5:
                                image_new[x,y] = palette_5
                            elif pix == oak_6:
                                image_new[x,y] = palette_6
                            elif pix == oak_7:
                                image_new[x,y] = palette_7
                            else:
                                image_new[x,y] = input_img_loaded[x,y]
                else:
                    img_new = input_img
                    
                save_path = template.replace("oak", plank.split("\\")[-1].replace(".png","")).replace("_planks","")
                img_new.save(save_path)
                print(f"saved {save_path}")




