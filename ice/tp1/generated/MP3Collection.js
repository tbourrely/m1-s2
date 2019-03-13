//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.2
//
// <auto-generated>
//
// Generated from file `MP3Collection.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

/* eslint-disable */
/* jshint ignore: start */

(function(module, require, exports)
{
    const Ice = require("ice").Ice;
    const _ModuleRegistry = Ice._ModuleRegistry;
    const Slice = Ice.Slice;

    let MP3Collection = _ModuleRegistry.module("MP3Collection");

    const iceC_MP3Collection_Track_ids = [
        "::Ice::Object",
        "::MP3Collection::Track"
    ];

    MP3Collection.Track = class extends Ice.Value
    {
        constructor(artist = "", name = "", year = "", file = "")
        {
            super();
            this.artist = artist;
            this.name = name;
            this.year = year;
            this.file = file;
        }

        _iceWriteMemberImpl(ostr)
        {
            ostr.writeString(this.artist);
            ostr.writeString(this.name);
            ostr.writeString(this.year);
            ostr.writeString(this.file);
        }

        _iceReadMemberImpl(istr)
        {
            this.artist = istr.readString();
            this.name = istr.readString();
            this.year = istr.readString();
            this.file = istr.readString();
        }
    };

    Slice.defineValue(MP3Collection.Track, iceC_MP3Collection_Track_ids[1], false);

    const iceC_MP3Collection_Collection_ids = [
        "::Ice::Object",
        "::MP3Collection::Collection"
    ];

    MP3Collection.Collection = class extends Ice.Object
    {
    };

    MP3Collection.CollectionPrx = class extends Ice.ObjectPrx
    {
    };

    Slice.defineOperations(MP3Collection.Collection, MP3Collection.CollectionPrx, iceC_MP3Collection_Collection_ids, 1,
    {
        "search": [, , , , ["MP3Collection.Track", true], [[7], [7]], , , , true],
        "add": [, , , , , [["MP3Collection.Track", true]], , , true, ],
        "remove": [, , , , , [["MP3Collection.Track", true]], , , true, ]
    });
    exports.MP3Collection = MP3Collection;
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require :
 (typeof WorkerGlobalScope !== "undefined" && self instanceof WorkerGlobalScope) ? self.Ice._require : window.Ice._require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports :
 (typeof WorkerGlobalScope !== "undefined" && self instanceof WorkerGlobalScope) ? self : window));