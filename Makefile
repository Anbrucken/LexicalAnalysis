SRC_DIR := src
OUT_DIR := out

SOURCES := $(shell find $(SRC_DIR) -name "*.java")


all: $(SOURCES)
	@mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

run: all
	java -cp $(OUT_DIR) Main

clean:
	rm -rf $(OUT_DIR)

.PHONY: all run clean