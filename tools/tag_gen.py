import util
import json
from copy import deepcopy

all_required = False

tag_template = {
    "replace": False,
    "values": []
}
class PrettyTag:
    def __init__(self):
        self.tag = "{\n\t\"replace\": false,\n\t\"values\": ["

    def add_raw_id(self, id_: str):
        self.tag = self.tag + "\n\t\t" + str(required(id_)) + ","
        return self
        
##    def add_id(self, id_: str):
##        self.tag = self.tag + "\n\t\t" + str(required(util.get_id(util.namespace, id_ + "_" + furniture))) + ","
##        return self
##    
##    def add_list(self, iterable: list):
##        for id_ in iterable:
##            self.tag = self.tag + "\n\t\t" + str(required(util.get_id(util.namespace, id_ + "_" + furniture))) + ","
##        return self
    
    def end(self):
        self.tag = self.tag[:-1]
        self.tag = self.tag + "\n\t]\n}\n"
        return self.tag
    
def required(name: str):
    if all_required:
        return f'"{name}"'
    return '{"id": "' + name + '", "required": false}'

def furniture(extras: list = [], furnitures: list = []):
    for furniture in furnitures:
        tag = PrettyTag()
        for extra in extras:
            tag = tag.add_raw_id(util.get_id(util.namespace, extra + "_" + furniture))
        tag = tag.end()

        tag_name = util.get_plural(furniture)
    
        block_tag = util.tag_mod_blocks / tag_name
        item_tag = util.tag_mod_items / tag_name
        
        util.save_json(block_tag, tag)
        util.save_json(item_tag, tag)
            
def non_flammable_wood(planks: list = [], furnitures: list = []):
    tag = PrettyTag()
    for plank in planks:
        if plank not in ["warped", "crimson"]:
            continue
        
        for furniture in furnitures:
            tag = tag.add_raw_id(util.get_id(util.namespace, plank + "_" + furniture))
            
    tag = tag.end()


    block_tag = util.tag_blocks_non_flammable_wood
    item_tag = util.tag_items_non_flammable_wood
    
    util.save_json(block_tag, tag)
    util.save_json(item_tag, tag)

furniture(util.get_versioned_list(util.planks), util.get_versioned_list(util.wooden_furnitures))
furniture(util.colors, util.get_versioned_list(util.colored_furnitures))

non_flammable_wood(util.get_versioned_list(util.planks), util.get_versioned_list(util.wooden_furnitures))
