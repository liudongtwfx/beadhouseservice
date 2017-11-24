/*
 * Copyright 2004-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
Spring = {};
Spring.debug = true;
Spring.decorations = {};
Spring.decorations.applied = false;
Spring.initialize = function () {
    Spring.applyDecorations();
    Spring.remoting = new Spring.RemotingHandler();
};
Spring.addDecoration = function (_1) {
    if (!Spring.decorations[_1.elementId]) {
        Spring.decorations[_1.elementId] = [];
        Spring.decorations[_1.elementId].push(_1);
    } else {
        var _2 = false;
        for (var i = 0; i < Spring.decorations[_1.elementId].length; i++) {
            var _3 = Spring.decorations[_1.elementId][i];
            if (_3.equals(_1)) {
                if (_3.cleanup != undefined) {
                    _3.cleanup();
                }
                Spring.decorations[_1.elementId][i] = _1;
                _2 = true;
                break;
            }
        }
        if (!_2) {
            Spring.decorations[_1.elementId].push(_1);
        }
    }
    if (Spring.decorations.applied) {
        _1.apply();
    }
};
Spring.applyDecorations = function () {
    if (!Spring.decorations.applied) {
        for (var _4 in Spring.decorations) {
            for (var x = 0; x < Spring.decorations[_4].length; x++) {
                Spring.decorations[_4][x].apply();
            }
        }
        Spring.decorations.applied = true;
    }
};
Spring.validateAll = function () {
    var _5 = true;
    for (var _6 in Spring.decorations) {
        for (var x = 0; x < Spring.decorations[_6].length; x++) {
            if (Spring.decorations[_6][x].widget && !Spring.decorations[_6][x].validate()) {
                _5 = false;
            }
        }
    }
    return _5;
};
Spring.validateRequired = function () {
    var _7 = true;
    for (var _8 in Spring.decorations) {
        for (var x = 0; x < Spring.decorations[_8].length; x++) {
            if (Spring.decorations[_8][x].widget && Spring.decorations[_8][x].isRequired() && !Spring.decorations[_8][x].validate()) {
                _7 = false;
            }
        }
    }
    return _7;
};
Spring.AbstractElementDecoration = function () {
};
Spring.AbstractElementDecoration.prototype = {
    elementId: "",
    widgetType: "",
    widgetModule: "",
    widget: null,
    widgetAttrs: {},
    apply: function () {
    },
    validate: function () {
    },
    isRequired: function () {
    },
    equals: function (_9) {
    }
};
Spring.AbstractValidateAllDecoration = function () {
};
Spring.AbstractValidateAllDecoration.prototype = {
    event: "", elementId: "", apply: function () {
    }, cleanup: function () {
    }, handleEvent: function (_a) {
    }, equals: function (_b) {
    }
};
Spring.AbstractCommandLinkDecoration = function () {
};
Spring.AbstractCommandLinkDecoration.prototype = {
    elementId: "", linkHtml: "", apply: function () {
    }, submitFormFromLink: function (_c, _d, _e) {
    }, equals: function (_f) {
    }
};
Spring.AbstractAjaxEventDecoration = function () {
};
Spring.AbstractAjaxEventDecoration.prototype = {
    event: "",
    elementId: "",
    sourceId: "",
    formId: "",
    popup: false,
    params: {},
    apply: function () {
    },
    cleanup: function () {
    },
    submit: function (_10) {
    },
    equals: function (_11) {
    }
};
Spring.AbstractRemotingHandler = function () {
};
Spring.AbstractRemotingHandler.prototype = {
    submitForm: function (_12, _13, _14) {
    }, getLinkedResource: function (_15, _16, _17) {
    }, getResource: function (_18, _19, _1a) {
    }, handleResponse: function () {
    }, handleError: function () {
    }
};