(:*******************************************************:)
(: Test: K-SeqExprCast-1132                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gDay to xs:dayTimeDuration isn't allowed. :)
(:*******************************************************:)
xs:gDay("---03") cast as xs:dayTimeDuration