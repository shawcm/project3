BIN := MaxMinTemperature
OUT := out
SRC = src/*.java

.PHONY: all
all: $(SRC)
	javac -classpath `hadoop classpath` -d $(OUT) $(SRC)
	jar -cvf ${BIN}.jar -C $(OUT)

.PHONY: clean
clean:
	rm -rf $(OUT) *.jar
