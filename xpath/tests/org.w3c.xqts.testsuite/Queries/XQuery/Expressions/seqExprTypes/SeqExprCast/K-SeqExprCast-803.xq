(:*******************************************************:)
(: Test: K-SeqExprCast-803                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: It is not possible to extract an Effective Boolean Value from the type xs:dateTime, with the boolean() function. :)
(:*******************************************************:)
boolean(xs:dateTime("2002-10-10T12:00:00-05:00"))