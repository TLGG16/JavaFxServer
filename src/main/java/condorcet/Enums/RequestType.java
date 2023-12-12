package condorcet.Enums;

public enum RequestType {
        LOGINADMIN,
        ADMINCREATEADMIN,


        //TODO Часть Админа
        ADMINMENUACCOUNTSADMINS,
        ADMINMENUACCOUNTSACCOUNTANTS,
        ADMINMENUACCOUNTSCLIENTS,

        ADMINMENUSUPPLIERS,

        ADMINMENUPRODUCTS,

        ADMINMENUREPORTS,

        ADMINMENUREVIEWS,

        ADMINMENUORDERS,



        //TODO Часть бухгалтера
        ACCOUNTANTLOGIN,
        ACCOUNTANTREPORT,
        ACCOUNTANTVIEWORDERS,
        ACCOUNTANTVIEWFINALORDERS,
        DELIVERYPRICE,



        //TODO Часть клиента
        CLIENTLOGIN,
        CLIENTORDER,
        CLIENTREVIEW,


        TEST,

        //TODO удалить эти части
        REGISTER,
        LOGIN
}

