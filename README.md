# Cryptocurrencies

Cryptocurrencies is an App that allows you so view cryptocurrencies prices and details.

## Features

- Offline first app. Pull to refresh new data.
- Searchable list of cryptocurrencies.
- Cryptocurrencies details.

## Arquitecture

#### This App is designed in Clean Arquitecture on top of MVVM.

[![N|Solid](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

It's composed of four modules:

- data - Data layer. This is where the repositories are implemented. DAOs and APis are also defined
  here.
- domain - Business rules. It's where the UseCases and Repository interfaces are located.
- app - View implementation details and viewmodels.

##### These modules were defined this way to provide us some guarantees:

> The Dependency Rule states that source code dependencies can only point inwards. This means no
> inner circle can refer in any way anything in an outer circle. Using modules we can ensure the
> dependency rule is respected.

Starting with the domain module, it has no dependencies outwards.   
Data module only depends on the domain module.  
App module depends on both domain and data modules.

## Run

- Clone this repo.  
- Open project in Android Studio.  
- Run the App.

## Build

In order to build the project, run unit tests and assemble debug and release APKs execute the
following command:

```sh
./gradlew build
```

## Test

In order to run unit tests, run the following command:

```sh
./gradlew test
```

## Documentation

Domain module is documented with KDoc on all public interfaces.  
Remaining modules have no strict documentation rules. Documentation shall de provided as the
developer sees fit.