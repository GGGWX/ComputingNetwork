# Assignment6

## P1

Suppose the information content of a packet is the bit pattern 1010 0111 0101 1001 and an even parity scheme is being used. What would the value of the field containing the parity bits be for the case of a two-dimensional parity scheme? Your answer should be such that a minimum-length checksum field is used. 

## S1

![](/Users/gggwx/Downloads/IMG_5255.jpg)



## P7

In this problem, we explore some of the properties of the CRC. For the generator G (= 1001) given in Section 6.2.3, answer the following questions. 

1. Why can it detect any single bit error in data D? 
2. Can the above G detect any odd number of bit errors? Why 

## S7

a) A single bit error will occur when the received data is D*2r XOR R + 2i. It's obvious that if we divide this result by G, its reminder is not zero. If G contains more than one 1, a single bit error will be detected anyway.

b) Because 1001 can be divided by 11, however, any number with odd-number 1's cannot be divided by 11. Thus, G cannot detect any odd number of bit errors.



## P8

In Section 6.3, we provided an outline of the derivation of the efficiency of slotted ALOHA. In this problem we’ll complete the derivation. 

1. Recall that when there are N active nodes, the efficiency of slotted ALOHA is Np(1 - p)N-1. Find the value of p that maximizes this expres- sion. 
2. Using the value of p found in (a), find the efficiency of slotted ALOHA by letting N approach infinity. Hint: (1 - 1/N)N approaches 1/e as N approaches infinity. 

## S8

![](/Users/gggwx/Downloads/IMG_5256.jpg)



## P9

Show that the maximum efficiency of pure ALOHA is 1/(2e). Note: This problem is easy if you have completed the problem above! 

## S9

![](/Users/gggwx/Downloads/IMG_5257.jpg)

## P14

Consider three LANs interconnected by two routers, as shown in Figure 6.33. 

1. AssignIPaddressestoalloftheinterfaces.ForSubnet1use addresses of the form 192.168.1.xxx; for Subnet 2 uses addresses of the form 192.168.2.xxx; and for Subnet 3 use addresses of the form 192.168.3.xxx. 
2. Assign MAC addresses to all of the adapters. 
3. Consider sending an IP datagram from Host E to Host B. Suppose all of the ARP tables are up to date. Enumerate all the steps, as done for the single-router example in Section 6.4.1. 
4. Repeat (c), now assuming that the ARP table in the sending host is empty (and the other tables are up to date). 

![](/Users/gggwx/Desktop/屏幕快照 2019-05-14 上午9.06.09.png)

## S14

![](/Users/gggwx/Downloads/屏幕快照 2019-05-14 上午9.06.09.jpg)

c)

1. Packet sent to Router 2, through interface 192.168.3.103
2. Adapter in Host E gets MAC address 99-99-99-99-99-99 of this interface
3. Router2 receives the packet and the datagram, and datagram will be routed to 192.168.2.103
4. Router2 sends packet with MAC address 55-55-55-55-55-55 and source address 66-66-66-66-66-66 with IP address 192.168.2.104
5. In a similar way, the process continues until the datagram reaches Host B

d) ARP in E must now determine the MAC address of 198.162.3.103. Host E sends out an ARP query packet within a broadcast Ethernet frame. Router 2 receives the query packet and sends to Host E an ARP response packet. This ARP response packet is carried by an Ethernet frame with Ethernet destination address 77-77-77-77-77-77.



