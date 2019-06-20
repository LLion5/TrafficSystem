import serial

import port

serialport = serial.Serial ("com3", 9600, timeout=1)

port_data = ''
try:
    print ('start recive')
    while True:
        if serialport.inWaiting ()==5:
            temp = str (serialport.read (5))
        elif serialport.inWaiting() == 7:
            temp = str(serialport.read(7))
            if list(temp)[11] == '\'':
                continue
            print("经过的小车ID和基站号码：",temp[11:len (temp) - 1])
            port.port_to_data(temp[11:13],temp[14])


except EnvironmentError as err:
    print (err)
finally:
    serialport.close ()

