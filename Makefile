.PHONY: default compose-up build compose-down

export TARGET_APPLICATION_URL=http://login-form:8080

default: build

compose-up: compose-down
	docker-compose up -d

compose-down:
	docker-compose down

build: compose-up
	BROWSER=FIREFOX ./gradlew clean test
	BROWSER=CHROME  ./gradlew clean test

vnc-chrome:
	echo "For Chrome, Open address 'localhost:15900' with password 'secret'"
	echo "For Firefox, Open address 'localhost:25900' with password 'secret'"
	/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --app-id=iabmpiboiopbgfabjmgeedhcmjenhbla &
