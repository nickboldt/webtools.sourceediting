(:*******************************************************:)
(: Test: K-ErrorFunc-7                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A test whose essence is: `if(false()) then error((), "description") else true()`. :)
(:*******************************************************:)
if(false()) then error((), "description") else true()