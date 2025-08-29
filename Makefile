.PHONY: build up down clean remove-image logs

build-test:
	@echo "\033[0;36mRunning all tests...\033[0m"
	(cd user && gradle clean build)
	@echo "\033[0;32mAll tests completed successfully!\033[0m"
