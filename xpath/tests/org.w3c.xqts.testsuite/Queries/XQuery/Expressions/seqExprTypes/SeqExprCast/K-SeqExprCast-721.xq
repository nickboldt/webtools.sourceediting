(:*******************************************************:)
(: Test: K-SeqExprCast-721                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:yearMonthDuration as source type and xs:time as target type should always evaluate to false. :)
(:*******************************************************:)
not(xs:yearMonthDuration("P1Y12M") castable as xs:time)