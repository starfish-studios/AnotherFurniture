from pathlib import Path
import os

import sided_helper
import texture_gen


def load_properties(filepath, sep='=', comment_char='#'):
    """
    Read the file passed as parameter as a properties file.
    """
    props = {}
    with open(filepath, "rt") as f:
        for line in f:
            l = line.strip()
            if l and not l.startswith(comment_char):
                key_value = l.split(sep)
                key = key_value[0].strip()
                value = sep.join(key_value[1:]).strip().strip('"') 
                props[key] = value 
    return props

project_dir = Path(os.getcwd()).parent.absolute()
namespace = load_properties(f"{project_dir}\\gradle.properties")["archives_base_name"]

def main():
    sided_helper.ensure_outputs_exist(f"{project_dir}\\forge\\src\\main\\resources\\assets")
    sided_helper.convertForgeRenderTypes(f"{project_dir}\\forge\\src\\main\\resources\\assets\\{namespace}\\models\\block\\template")
    texture_gen.generate_wood_types(
        plank_path = "C:\\Users\\jacec\\Desktop\\planks",
        template_path = "C:\\Users\\jacec\\Desktop\\another_furniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block",
        banned_variants = ["chair", "shutter"]) # stops overriding textures that have custom patterns for each variant
    

    
if __name__ == "__main__":
    main()
