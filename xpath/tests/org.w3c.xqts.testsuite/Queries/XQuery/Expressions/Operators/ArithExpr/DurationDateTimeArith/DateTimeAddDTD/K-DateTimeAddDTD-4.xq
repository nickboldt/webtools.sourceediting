(:*******************************************************:)
(: Test: K-DateTimeAddDTD-4                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The '+' operator is not available between xs:dateTime and xs:time. :)
(:*******************************************************:)
xs:dateTime("1999-10-12T08:12:12") + xs:time("08:12:12")