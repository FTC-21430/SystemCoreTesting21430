from matplotlib import pyplot as plt
import csv
import datetime
import numpy as np

chub = "bench3_23_26.csv"
systemCore = "systemcorePlace.csv"
fname = str(datetime.datetime.now()).replace(" ", "_").replace(".", "-").replace(":", "-")
title = f"ChubSystemcoreBenchCompare{fname}.jpg"


def read_last_row(path):
    with open(path, "r", newline="", encoding="utf-8") as file:
        rows = list(csv.reader(file))

    headers = [header.strip() for header in rows[0][1:]]
    values = {
        header: float(value.strip())
        for header, value in zip(headers, rows[-1][1:])
        if value.strip()
    }
    return headers, values


def visualize():
    chub_headers, chub_values = read_last_row(chub)
    system_headers, system_values = read_last_row(systemCore)

    headers = [header for header in chub_headers if header in system_values]
    if not headers:
        raise ValueError("No shared benchmark columns were found between the CSV files.")

    averages_chub = [chub_values[header] for header in headers]
    averages_system = [system_values[header] for header in headers]
    x_axis = np.arange(len(headers))

    plt.figure(figsize=(14, 7))
    chub_bar = plt.bar(x_axis - 0.2, averages_chub, 0.4, label="CHUB")
    system_bar = plt.bar(x_axis + 0.2, averages_system, 0.4, label="SystemCore")
    plt.xticks(x_axis, headers, rotation=30)
    plt.ylim(0, max(max(averages_chub), max(averages_system)) + 40)
    plt.xlabel("Tests")
    plt.ylabel("Time (ms)")
    plt.title(f"Timing Benchmarks, CHUB:\"{chub}\" vs SystemCore:\"{systemCore}\"")
    plt.bar_label(chub_bar)
    plt.bar_label(system_bar)
    plt.legend()
    plt.savefig(title)
    plt.show()


visualize()