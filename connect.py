import sys,os
if __name__ == '__main__':
    import socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        sock.connect((sys.argv[1],int(sys.argv[2])))
    except Exception,e:
        print e
        print "connected failed"
    else:
        print "connected ok"
        sock.close()
