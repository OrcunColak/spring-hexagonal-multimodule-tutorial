# common-definitions
Definitons that are used by **core-module** are defined in **core-api**. But sometimes we need definitions that are used or shared by modules. 

For example **data-access** brings in some enumerations such as **CommandEnum**. This enum is also sent over **sys-intf-module**. In this case, if we define the enumeratin in the **core-api** the  **data-access** will have dependency on **core-api**
but we don't want this dependency, because **data-access** is very generic and we want it to stay this way. Therefore definitions that come from data-access are defined here
