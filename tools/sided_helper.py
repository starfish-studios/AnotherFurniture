import jstyleson as json
import os



def ensure_outputs_exist(input_path, replace1 = "forge", replace2 = "fabric"):
    for file in os.walk(input_path):
        try:
            os.mkdir(file[0].replace(replace1, replace2))
        except:
            continue

def convertForgeRenderTypes(path):
    input_files = [os.path.join(path, name) for path, subdirs, files in os.walk(path) for name in files if name.endswith(".json")]
    for input_file in input_files:
        f = open(input_file)
        model = json.load(f)
        model.pop("render_type", None)
        
        output_file = input_file.replace("forge", "fabric")
        with open(output_file, "w+") as f:
            f.write(json.dumps(model) + "\n")
            f.close()
