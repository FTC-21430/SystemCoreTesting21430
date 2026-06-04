with open("cleaned_clean_log.txt","r") as fin:
    lines = fin.readlines()
    csvPlaces = ["switch_motor","set","doubles","doubles2","blackboard","i2c"]
    rows = []
    rows.append(["name"]+csvPlaces)
    row = []
    for i in range(len(lines)):
        line = lines[i]
        title = csvPlaces[i%len(csvPlaces)]
        value = line.replace(title,"").strip()
        if (title == csvPlaces[0]):
            row = ["sc",value]
        elif (title == csvPlaces[-1]):
            row.append(value)
            rows.append(row)
        else:
            row.append(value)
    print(rows)
    
    with open("systemcorePlace.csv","w") as fout:
        for line in rows:
            fout.write(",".join(line) + "\n")