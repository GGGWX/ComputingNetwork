import socket

s = socket.socket()

host = socket.gethostname() #get localhost
port = 12345
s.bind((host, port)) #bind port and return a tuple

s.listen(5)

while True:
    c, addr = s.accept() # create client connection
    print("host address:")
    print(addr)
    test = 'hello'.encode(encoding="utf-8") # convert str to bytesObj
    c.send(test)
    c.close()