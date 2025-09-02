.PHONY: build up down clean test ci-test-suite

setup: build up

build:
	@echo "\033[0;36mBuilding applications...\033[0m"
	python build.py || python3 build.py
	@echo "\033[0;32mBuild and setup completed!\033[0m"

up:
	@echo "\033[0;36mSetting up the environment...\033[0m"
	docker-compose up -d --build
	@echo "\033[0;32mEnvironment setup completed!\033[0m"

down:
	@echo "\033[0;36mTearing down the environment...\033[0m"
	docker-compose down
	@echo "\033[0;32mEnvironment torn down!\033[0m"

clean:
	@echo "\033[0;36mCleaning up build artifacts...\033[0m"
	(cd bff && ./gradlew clean)
	(cd user && ./gradlew clean)
	(cd inventory && ./gradlew clean)
	(cd validation && ./gradlew clean)
	(cd payment && ./gradlew clean)
	(cd order && ./gradlew clean)
	@echo "\033[0;32mCleanup completed!\033[0m"

test:
	@echo "\033[0;36mRunning all tests...\033[0m"
	(cd bff && ./gradlew clean test)
	(cd user && ./gradlew clean test)
	(cd inventory && ./gradlew clean test)
	(cd validation && ./gradlew clean test)
	(cd payment && ./gradlew clean test)
	(cd order && ./gradlew clean test)
	@echo "\033[0;32mAll tests completed successfully!\033[0m"

ci-test-suite:
	@echo "\033[0;36mRunning all tests in CI mode...\033[0m"
	(cd bff && ./gradlew clean test --no-daemon)
	(cd user && ./gradlew clean test --no-daemon)
	(cd inventory && ./gradlew clean test --no-daemon)
	(cd validation && ./gradlew clean test --no-daemon)
	(cd payment && ./gradlew clean test --no-daemon)
	(cd order && ./gradlew clean test --no-daemon)
	@echo "\033[0;32mAll CI tests completed successfully!\033[0m"
