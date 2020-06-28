BIN := MaxMinTemperature
OUT := out
SRC = src/*.java

.PHONY: all
all: $(BIN)


$(BIN): $(SRC)
	javac -classpath `hadoop classpath` -d $(OUT) $(SRC)
	jar -cvf ${BIN}.jar -C $(OUT)

.PHONY: clean
clean:
	rm -rf $(OUT) *.jar
