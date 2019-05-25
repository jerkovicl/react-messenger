# Project info

## Setup

- clone project `git clone github.com/jerkovicl/react-messenger.git && cd react-messenger`
- mysql dump file and scripts are in `_resources` folder
- start Spring Boot App with `mvnw spring-boot:run`
- start React App with `cd app && npm start`

## Slack settings

- **Slack webhook url:** url needs to be entered via system environment variable `SLACK_HOOKURL` for app to work
- **Slack webhook example:**  
  `curl -X POST -H 'Content-type: application/json' --data '{"text":"Hello, World!"}' https://hooks.slack.com/services/XXXXXX/XXXXXXX/XXXXXXXXXX`
