package ee.mario.sharedblogbackend.entity;

public enum AccountType {
    REGULAR, ADMIN, SUPERADMIN
    // 0. regular ---> saab postitada ja kõik
    // 1. admin ---> saab vaadata postitusi üle (approve) ja kustutada, muuta
    // 2. superadmin ---> saab anda regular inimestele õigusi, et nad oleks admin

}
