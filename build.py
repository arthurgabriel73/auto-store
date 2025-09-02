import os
import threading

threads = []


def build_application(app):
    threads.append(app)
    print("Building application {}".format(app))
    os.system("cd {} && gradle build -x test".format(app))
    print("Application {} finished building!".format(app))
    threads.remove(app)

def build_all_applications():
    print("Starting to build applications!")
    threading.Thread(target=build_application, args={"bff"}).start()
    threading.Thread(target=build_application, args={"inventory"}).start()
    threading.Thread(target=build_application, args={"order"}).start()
    threading.Thread(target=build_application, args={"payment"}).start()
    threading.Thread(target=build_application, args={"user"}).start()
    threading.Thread(target=build_application, args={"validation"}).start()

if __name__ == "__main__":
    print("Pipeline started!")
    build_all_applications()
    while len(threads) > 0:
        pass
