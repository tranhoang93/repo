import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import translationEn from './messages/messages_en';
import translationVi from './messages/messages_vi';

const resources = {
    en: {
        translation: translationEn
    },
    vi: {
        translation: translationVi
    }
};

i18n
    .use(initReactI18next) // passes i18n down to react-i18next
    .init({
        resources,
        lng: "vi",

        keySeparator: false, // we do not use keys in form messages.welcome

        interpolation: {
            escapeValue: false // react already safes from xss
        }
    });

export default i18n;