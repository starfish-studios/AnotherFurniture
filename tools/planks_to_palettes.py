from PIL import Image
import os
import uuid
import json

class Pos2:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    def set_x(self, x):
        self.x = x

    def set_y(self, y):
        self.y = y

    def __str__(self):
     return f"Pos2[x={self.x}, y={self.y}]"

    def __repr__(self):
     return f"PaletteEntry[pos={self.pos}, color={self.color}]"

class ColorPalette:
    def __init__(self, name: str):
        self.name = name
        self.data = []
    
    def set_name(self, name: str):
        self.name = name

    def add_palette_data(self, data: tuple):
        self.data.append(data)

    def __str__(self):
     return f"ColorPalette[name={self.name}, data={self.data}]"


    def get_palette_from_image(self, palette_type):
        file_path = cwd + "palettes\\" + palette_type + "\\" + self.name + "_" + palette_type
        img = Image.open(path + "\\" + template_name + ".png").convert(mode="RGBA", dither=Image.Dither.NONE)
        img_loaded = img.load()
        img_size = img.size
        
        for y in range(img_size[0]):
            for x in range(img_size[1]):
                if not img_loaded[x, y] in self.data:
                    self.add_palette_data(img_loaded[x, y])
                    
        self.save_to_json()
        return color_palette


    def process_images(self, palette_type):
        folder_path = cwd + "palettes\\" + palette_type
        for image in [os.path.join(folder_path, f) for f in os.listdir(folder_path) if f.endswith(".png")]:
            image_path = folder_path + "\\" + image
            image_name_noex = image.replace(".png", "")
            color_palette = self.get_palette_data(palette_type)

       
    def load_from_json_raw(self):
        with open(cwd + "palettes\\planks\\palette.json") as f:
            json_data = json.load(f)
            return json_data


    def load_from_json(self):
        json_data = self.load_from_json_raw()
        for color in json_data[self.name]["data"]:
            self.add_palette_data((
                color["r"],
                color["g"],
                color["b"],
                color["a"]))
        return self


    def save_to_json(self):
        json_data = self.load_from_json_raw()
        json_data[self.name] = {"data":[]}
        for data_entry in self.data:
            color = {
                "r": data_entry[0],
                "g": data_entry[1],
                "b": data_entry[2],
                "a": data_entry[3]
            }
            json_data[self.name]["data"].append(color)
            
        with open(cwd + "palettes\\planks\\palette.json", "w+") as f:
            json.dump(json_data, f, indent=4)


    def save_palette_as_image(self):
        img = Image.new("RGBA", (len(self.data), 1), "white")
        img_loaded = new_img.load()
        for x, palette_entry in enumerate(self.data):
            img_loaded[x, 0] = palette_entry
        img.save(cwd + "palettes\\planks\\" + self.name + ".png")


    def swap_palette_to(self, palette_to_swap_to, image_type):
        pass

        

#process_images(cwd + "palettes\\planks", "_planks")

planks = [
    "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "crimson", "warped"
]
colors = [
    "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray",
    "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
]
cwd = os.getcwd() + "\\"

oak = ColorPalette("oak").load_from_json()
print(oak)
spruce = ColorPalette("spruce").load_from_json()
birch = ColorPalette("birch").load_from_json()
jungle = ColorPalette("jungle").load_from_json()
acacia = ColorPalette("acacia").load_from_json()
dark_oak = ColorPalette("dark_oak").load_from_json()
mangrove = ColorPalette("mangrove").load_from_json()
crimson = ColorPalette("crimson").load_from_json()
warped = ColorPalette("warped").load_from_json()

#print(oak.swap_palette_to(spruce, "planks"))
