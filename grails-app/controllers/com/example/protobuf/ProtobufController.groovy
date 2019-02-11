package com.example.protobuf

import com.example.tutorial.AddressBookProtos.Person
import com.example.tutorial.AddressBookProtos.AddressBook

import java.nio.file.Files

class ProtobufController {
	String filepathOfAddressBook = "/tmp/poc_protobuf_addressBook"

    def index() {
		buildAddressBook()
		render "CREATED!"
	}

	def read() {
		readAddressBook()
		render "READED!"
	}

	private buildPerson(id, name, email, phone, phoneType){
			Person.Builder person = Person.newBuilder();

			person.setId(id)
			person.setName(name);
			if (email.length() > 0) {
				person.setEmail(email);
			}

			Person.PhoneNumber.Builder phoneNumber =
					Person.PhoneNumber.newBuilder().setNumber(phone);

			if (phoneType.equals("mobile")) {
				phoneNumber.setType(Person.PhoneType.MOBILE);
			} else if (phoneType.equals("home")) {
				phoneNumber.setType(Person.PhoneType.HOME);
			} else if (phoneType.equals("work")) {
				phoneNumber.setType(Person.PhoneType.WORK);
			}

			person.addPhones(phoneNumber)
			return person.build();
	}

	private buildAddressBook(){
		AddressBook.Builder addressBook = AddressBook.newBuilder();
		// Read the existing address book.
		try {
			FileInputStream input = new FileInputStream(filepathOfAddressBook);
			try {
				addressBook.mergeFrom(input);
			} finally {
				try { input.close(); } catch (Throwable ignore) {}
			}
		} catch (FileNotFoundException e) {
			println("${filepathOfAddressBook} File not found. Creating a new file.");

			File file = new File(filepathOfAddressBook);
			file.createNewFile();
		}

		// Add an address.
		addressBook.addPeople(
			buildPerson(2, "mike szulc", "mike@email.lol", "123123123", "mobile")
		);

		addressBook.addPeople(
			buildPerson(3, "marioooo", "mario@email.lol", "123123123", "mobile")
		);
		// Write the new address book back to disk.
		FileOutputStream output = new FileOutputStream(filepathOfAddressBook, false);
		try {
			addressBook.build().writeTo(output);
		} finally {
			output.close();
		}
	}

	private String readAddressBook() {
		File file = new File(filepathOfAddressBook)
		if(file.exists()){
			byte[] fileContent = Files.readAllBytes(file.toPath())
			AddressBook addressBook
			try
			{
				addressBook = AddressBook.parseFrom(fileContent) //IOUtils.toByteArray(InputStream input)
				println "Number of people in the AddressBook: " + addressBook.getPeopleCount()
				addressBook.getPeopleList().each {
					Person person ->
						println "person.id: " + person.id
						println "person.name: " + person.name
						println "person.email: " + person.email
						println "phonesList*.number: " + person.phonesList*.number
				}
			}
			catch (ex)
			{
				println("ERROR!")
			}
			return addressBook
		}

	}
}
