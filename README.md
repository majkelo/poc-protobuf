#TWO WAYS OF INSTALLING:
	#USING SCRIPT ./scripts/install_protobuf.sh #it's installs the newset, the most stable version
	$ protoc --version
	libprotoc 3.6.1

	#OR 

	$ sudo apt-get install protobuf-compiler #it installs the old one, which is available in current OS repo

		The following NEW packages will be installed:
		libprotoc8 protobuf-compiler
		0 upgraded, 2 newly installed, 0 to remove and 94 not upgraded.
		Need to get 254 kB of archives.
		After this operation, 1,024 kB of additional disk space will be used.


#AFTER CREATING PROTO FILES YOU HAVE TO GENERATE .java FILES FROM THEM BY COMMAND:
	SRC_DIR=/home/baroo/work/poc/poc-protobuf/grails-app/utils/com/example/proto
	DST_DIR=/home/baroo/work/poc/poc-protobuf/grails-app/utils
	protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto
#this command should be run after every modification of the proto files. unfortunatelly it's gong to override the exisitng files, so if there were any custom changes/optimizations, they will be lost
