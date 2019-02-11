# TWO WAYS OF INSTALLING


  - USING SCRIPT added to this repo 
  **it installs the newest, the most stable version**
  
`./scripts/install_protobuf.sh`
  `$ protoc --version`
	`libprotoc 3.6.1`


  - install from OS repo 
  **it installs usually the old one, which is available in current OS repo**
  
  `$ sudo apt-get install protobuf-compiler`

# AFTER CREATING PROTO FILES YOU HAVE TO GENERATE .java FILES

`SRC_DIR=/home/baroo/work/poc/poc-protobuf/grails-app/utils/com/example/proto`

`DST_DIR=/home/baroo/work/poc/poc-protobuf/grails-app/utils`

`protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto`


this command should be run after every modification of the proto files. unfortunatelly it's gong to override the exisitng files, so if there were any custom changes/optimizations, they will be lost
