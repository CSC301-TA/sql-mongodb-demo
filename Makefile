run-sql:
	docker compose up --build
run-mongo:
	docker compose -f ./docker-compose-mongo.yml -p mongo up --build