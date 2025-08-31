.PHONY: build up down clean remove-image logs

test:
	@echo "\033[0;36mRunning all tests...\033[0m"
	#(cd user && ./gradlew clean test)
	(cd inventory && ./gradlew clean test)
	@echo "\033[0;32mAll tests completed successfully!\033[0m"

ci-test-suite:
	@echo "\033[0;36mRunning all tests in CI mode...\033[0m"
	(cd user && ./gradlew clean test --no-daemon)
	@echo "\033[0;32mAll CI tests completed successfully!\033[0m"
