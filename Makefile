all: PartitionSeq PartitionSmp

%.class: %.java
	javac -source 1.5 -target 1.5 $<

PartitionSeq: PartitionSeq.class

PartitionSmp: PartitionSmp.class
