package by.gourianova.monitorsensors.action;

import by.gourianova.monitorsensors.action.admin.ShowAdminPageAction;
import by.gourianova.monitorsensors.action.admin.role.AddRoleAction;
import by.gourianova.monitorsensors.action.admin.role.DeleteRoleAction;
import by.gourianova.monitorsensors.action.admin.sensor.*;
import by.gourianova.monitorsensors.action.admin.user.*;
import by.gourianova.monitorsensors.action.locale.ChangeLocaleAction;
import by.gourianova.monitorsensors.action.sensor.ShowAllSensorsByPageAction;
import by.gourianova.monitorsensors.action.sensor.*;
import by.gourianova.monitorsensors.action.user.LoginUserAction;
import by.gourianova.monitorsensors.action.user.LogoutUserAction;
import by.gourianova.monitorsensors.action.user.RegisterUserAction;


public enum ActionType {
    LOGIN(new LoginUserAction()),
    LOGOUT(new LogoutUserAction()),
    REGISTER(new RegisterUserAction()),
    CHANGE_LOCALE(new ChangeLocaleAction()),
    ADD_ROLE(new AddRoleAction()),

    ADD_SENSOR(new AddSensorAction()),
    ADD_TYPE(new AddTypeAction()),
    ADD_UNIT(new AddUnitAction()),

    EDIT_SENSOR(new EditSensorAction()),


    CHANGE_USER_ROLE(new ChangeUserRoleAction()),
    CHANGE_USER_DATA(new ChangeUserDataAction()),


    SHOW_ALL_USER_SENSORS_CHANGING(new ShowAllUserSensorsAction()),
    SHOW_ALL_SENSORS(new ShowAllSensorsAction()),
    SHOW_ALL_TYPES(new ShowAllTypesAction()),
    SHOW_ALL_SENSOR_UNITS(new ShowAllUnitsAction()),
    SHOW_DELETE_SENSOR_PAGE(new ShowDeleteSensorPageAction()),
    SHOW_EDIT_SENSOR_PAGE(new  ShowEditSensorPageAction()),

    UPDATE_SENSOR(new UpdateSensorAction()),
    FIND_SENSOR(new FindSensorAction()),

    SHOW_ALL_USERS(new ShowAllUserAction()),
    SHOW_ALL_ROLES(new ShowAllRoleAction()),

    SHOW_ADMIN_PAGE(new ShowAdminPageAction()),


    SHOW_ADD_SENSOR_PAGE(new ShowAddSensorPageAction()),
    SHOW_SENSORS_PAGE(new ShowAllSensorsByPageAction()),

    SHOW_DELETE_USER_PAGE(new ShowDeleteUserPageAction()),

    FIND_USER(new FindUserAction()),

    DELETE_USER(new DeleteUserAction()),

    DELETE_ROLE(new DeleteRoleAction()),

    DELETE_SENSOR(new DeleteSensorAction()),

    DELETE_TYPE(new DeleteTypeAction()),


    DELETE_UNIT(new DeleteUnitAction()),

    SHOW_USER_CHANGE_ROLE_PAGE(new ShowChangeRolePage()),

    GET_USER_DATA(new GetUserDataAction()),

    GET_SENSORS_DATA(new GetSensorDataAction());


    Action action;

    ActionType(Action action) {
        this.action = action;
    }

    Action getAction() {
        return action;
    }

}
