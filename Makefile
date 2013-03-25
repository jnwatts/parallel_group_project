all: PartitionSeq

%.class: %.java
	javac -source 1.5 -target 1.5 $<

PartitionSeq: PartitionSeq.class
