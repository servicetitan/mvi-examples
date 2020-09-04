import "reflect-metadata";
import 'semantic-ui-css/semantic.min.css'
import React from 'react';
import ReactDOM from 'react-dom';
import { initIoc } from 'src/core/ioc';
import { Root } from 'src/ui/root';
import { instantiateEventProcessors } from "./core/event-processors";
import { EventDispatcher } from './core/event-dispatcher';

const eventDispatcher = new EventDispatcher();
const iocContainer =  initIoc(eventDispatcher);
const eventProcessors = instantiateEventProcessors(eventDispatcher);

(window as any).iocContainer = iocContainer;
(window as any).eventProcessors = eventProcessors;

ReactDOM.render(
  <React.StrictMode>
    <Root />
  </React.StrictMode>,
  document.getElementById('root')
);
