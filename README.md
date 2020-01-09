# Online Medication Platform

## Prerequisites

The following dependencies must be installed for development purpose:

* NodeJS and NPM

    Install `Nodejs` and `npm` if the are not already installed on your machine.
    Can be download and installed from the [official site](https://nodejs.org/en/download/)
    Please, make sure you have installed `Node.js 6.9.0` and `npm 3.0.0` or higher.

    _Note:_ _version used during development, `Node.js v10.14.2` and `npm v6.9.3`_

* Angular-CLI

    Install the Angular-CLI globally by running
    ```
    npm install -g @angular/cli
    ```
	_**(*):** Not mandatory during build integrations_

* Install project dependencies by executing
    ```
    npm install
    ```
* Install RabbitMQ and Erlang from the [official site](https://www.rabbitmq.com/install-windows.html) _(for Windows OS)_

## Development

### Starting the Development server

To preview the application in the browser, navigate to the root directory and execute the following on the command line

```
ng serve
```

Navigate to `http://127.0.0.1:4200/`. The app will automatically reload if you change any of the source files.

_**(*): Do not forget to start the backend-service before opening the dashboard in the browser**_

### Project Structure

```bash

├── README.md
│
├── src                     #Source
│   │
│   ├── app                 #Application source
│   │
│   │   ├── authentication  #Authentication Module (include login component)
│   │   │
│   │   ├── caregiver       #Caregiver Module (include caregiver list/details components)
│   │   │
│   │   ├── doctor       	#Doctor Module (include doctor list/details components)
│   │   │
│   │   ├── medication      #Medication Module (include medication list/details components)
│   │   │
│   │   ├── patient       	#Patient Module (include patient list/details components)
│   │   │
│   │   ├── dashboard       #Dahsboard Module (include the application layout: header/main component)
│   │   │
│   │   └── shared          #Shared assets (components/models/interfaces/services)
│   │
│   ├── assets              #External assets (CSS, Images, Fonts, Scripts)
│   │
│   ├── environments        #Environment configurations (DEV and PROD)
│   │
│   ├── scss                #Common styling files
│   │
│   ├── favicon.ico
│   ├── index.html
│   ├── main.ts
│   ├── polyfills.ts
│   ├── styles.scss         #Main style file
│   └── tsconfig.app.json
│
├── tsconfig.json
└── tslint.json
```

### Generating new modules, components, Directives, Pipes and Services:

You cans use the `ng generate` (or just `ng g`) command to generate new components. Check out the [documentation](https://github.com/angular/angular-cli#generating-components-directives-pipes-and-services)

## Further help

To get more help on the Angular CLI use:

* `ng help` from the command line
* go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md)
* and also the [Ultimate Angular CLI Guide](https://www.sitepoint.com/ultimate-angular-cli-reference/) from SitePoint
