/**
 * Application services act as the facade for the Bounded Context’s Domain Model. They provide façade services to dispatch Commands/Queries to the underlying Domain Model. They are also the place where we place outbound calls to other Bounded Contexts as part of the processing of a Command/Query.
 * <p>
 * • Participate in Command and Query Dispatching <br>
 * • Invoke infrastructural components where necessary as part of the Command/Query processing <br>
 * • Provide Centralized concerns (e.g., Logging, Security, Metrics) for the underlying Domain Model <br>
 * • Make callouts to other Bounded Contexts <br>
 *
 * @since 1.0
 * @author sam
 * @version 1.0
 */
package com.example.demo.cartms.application;
