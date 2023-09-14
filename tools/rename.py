import os

planks = [
    "oak",
    "spruce",
    "birch",
    "jungle",
    "acacia",
    "dark_oak",
    "mangrove",
    "cherry",
    "bamboo",
    "crimson",
    "warped"
]

for plank in planks:
    print(plank + ".png", plank + "_single.png")
    if not os.path.exists(plank + ".png"):
        continue
    os.rename(plank + ".png", plank + "_single.png")
