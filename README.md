## Overview

The Serenity Screenplay API Test Framework is a sophisticated tool designed to streamline API testing workflows using the Serenity Screenplay pattern. This framework leverages the power of Serenity Screenplay and Serenity REST to provide a clean and expressive approach to writing API tests. With a focus on readability, maintainability, and flexibility, the solution abstracts away HTTP interactions and offers a fluent, chainable API for defining test scenarios.

## Description

Key components of the framework include:

- **ActorClient**: A wrapper class that facilitates actor interactions and enables test chaining. The ActorClient abstracts away the complexity of working with Serenity actors, providing a user-friendly interface for defining test steps and validations.


- **Clients**: Abstractions of API interactions provided to the ActorClient. Clients encapsulate common API tasks, such as sending requests and handling responses, making it easy to reuse and maintain test logic across test suites.


- **Asserter**: A utility class that provides AssertCondition instances to the ActorClient for conducting validations. The Asserter simplifies the process of defining assertions on API responses, promoting code reusability and readability.

## Steps to run the test

git clone https://github.com/your/repository.git

cd project-directory

./gradlew test