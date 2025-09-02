.PHONY: build up down clean remove-image logs

test:
	@echo "\033[0;36mRunning all tests...\033[0m"
	(cd user && ./gradlew clean test)
	(cd inventory && ./gradlew clean test)
	(cd validation && ./gradlew clean test)
	(cd payment && ./gradlew clean test)
	(cd order && ./gradlew clean test)
	@echo "\033[0;32mAll tests completed successfully!\033[0m"

ci-test-suite:
	@echo "\033[0;36mRunning all tests in CI mode...\033[0m"
	(cd user && ./gradlew clean test --no-daemon)
	(cd inventory && ./gradlew clean test --no-daemon)
	(cd validation && ./gradlew clean test --no-daemon)
	(cd payment && ./gradlew clean test --no-daemon)
	(cd order && ./gradlew clean test --no-daemon)
	@echo "\033[0;32mAll CI tests completed successfully!\033[0m"

setup:
	@echo "\033[0;36mSetting up the environment...\033[0m"
	docker-compose up -d --build
	@echo "\033[0;32mEnvironment setup completed!\033[0m"
