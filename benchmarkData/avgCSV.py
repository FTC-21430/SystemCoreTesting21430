with open("systemcorePlace.csv") as file:
     lines = file.readlines()[1:]
     data = [0]*(len(lines[0].split(","))-1)
     print(lines)
     print(data)
     avg = len(lines)
     for line in lines:
         if line == "\n":
             continue
         l=line.replace("ms","").replace("\n","").split(",")

         print(l)
         for i in range(len(l)):
             if ("chub" in l[i] or "sc" in l[i]):
                 continue
             data[i-1]+=int(l[i])
     for v in range(len(data)):
         data[v] /= avg
     print(data)
