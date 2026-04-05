# Telegram Bot (Scala 3)

A Scala 3 Telegram bot project built with the `bot4s` Telegram framework. The repository includes a reusable bot base class, plus several example bots demonstrating common Telegram bot features such as commands, message handling, reminders, and shopping list management.

## Features

- Reusable `BasicBot` wrapper for Telegram polling bots
- Shopping list bot with commands and free-form message interaction
- Example bots for alarm scheduling, greeting, reversing text, photo sending, and message memory
- Uses `bot4s` with Scala 3 and polling-based Telegram integration

## Project structure

- `build.sbt` — Scala 3 build definition
- `bot_token.txt` — Telegram bot token file (must be created locally)
- `src/main/scala/s1/telegrambots/BasicBot.scala` — reusable Telegram bot helper
- `src/main/scala/s1/telegrambots/YourBot.scala` — shopping list bot example
- `src/main/scala/s1/telegrambots/examples/` — additional sample bot implementations
- `src/main/scala/s1/telegrambots/advanced/` — advanced bot examples

## Setup

1. Install Java 17+ and sbt.
2. Create a `bot_token.txt` file in the project root.
3. Paste your Telegram bot token into `bot_token.txt` and save it.

Example `bot_token.txt` content:

```
123456789:ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789
```

## Running the bot

From the repository root, start sbt and run:

```bash
sbt run
```

If prompted, choose the main class for the bot you want to run:

- `s1.telegrambots.YourBot`
- `s1.telegrambots.examples.AlarmBot`
- `s1.telegrambots.examples.RememberBot`
- `s1.telegrambots.examples.GreetBot`

You can also run a specific bot directly:

```bash
sbt "runMain s1.telegrambots.YourBot"
```

## YourBot commands

- `/start` — greet the user
- `/help` — show available commands
- `/show` — display the current shopping list
- `/clear` — clear the shopping list
- `/adding` — enable item entry until `End` is sent
- `/removing` — enable item removal by index until `End` is sent
- `/alarmIn [minutes]` — send the current list after the given delay

## Dependencies

- Scala 3.1.1
- `com.bot4s:telegram-core` 5.4.2
- `org.scalatest:scalatest` 3.2.9 (test scope)

## Notes

- `bot_token.txt` must be present in the project root and contain only your bot token.
- The bot runs in polling mode.
- Extend `BasicBot.scala` to add custom handlers or bot behavior.
