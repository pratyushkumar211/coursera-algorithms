# Variable Definitions
JAVA := java
JAVAC := javac
BUILD_SUFFIXES := .class .txt


# Java Source Files
JAVA_SRC := HelloWorld.java Hi.java NewSkeleton.java


## End of user specified files

# Append prefixes and Everything

# Java CLASS Files
JAVA_CLASS := $(JAVA_SRC:.java=.class)

# Java Output text files
JAVA_TXT := $(JAVA_SRC:.java=.txt)

# Cleanup Files
CLEAN_FILES := $(JAVA_CLASS) $(JAVA_TXT)
 

all: $(JAVA_TXT)
.PHONY: all

$(JAVA_TXT): %.txt: %.class
	@$(JAVA) $(subst .class,,$<) > $@

$(JAVA_CLASS): %.class: %.java
	@$(JAVAC) $<

clean:
	@echo "Cleaning up."
	@rm -f $(CLEAN_FILES)
.PHONY: clean


# Some learning Notes
# Use .PHONY to specify which targests are not treated as files
# Use @ in front of an action to suppress command output