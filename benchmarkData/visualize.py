from matplotlib import pyplot as plt
import datetime
import numpy as np
chub = "bench3_23_26.csv"
systemCore = "systemcorePlace.csv"
fname = str(datetime.datetime.now()).replace(" ","_").replace(".","-").replace(":","-")
title = f"ChubSystemcoreBenchCompare{fname}.jpg"
def visualize():
    with open(chub, "r") as f:
        with open(systemCore, "r") as f2:
            lines = f.readlines()
            headers, averages_chub = lines[0].split(",")[1:], [float(x) for x in lines[-1].split(",")[1:]]
            averages_system = [float(x) for x in f2.readlines()[-1].split(",")[1:]]
            x_axis = np.arange(len(headers))
            plt.figure(figsize=(14, 7))
            chub_bar = plt.bar(x_axis-0.2, averages_chub,0.4,label="CHUB")
            system_bar = plt.bar(x_axis+0.2, averages_system,0.4,label = "SystemCore")
            plt.xticks(x_axis, headers, rotation=30)
            plt.ylim(0,max(max(averages_chub),max(averages_system))+40)
            plt.xlabel("Tests")
            plt.ylabel("Time (ms)")
            plt.title(f"Timing Benchmarks, CHUB:\"{chub}\" vs SystemCore:\"{systemCore}\"")
            plt.bar_label(chub_bar)
            plt.bar_label(system_bar)
            plt.legend()
            plt.savefig(title)
            plt.show()
visualize()