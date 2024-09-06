--v3
--[[
boren
1
0
penxue
2
{delay,qianjin,0,0,0,4,0.000000}{default,gongji3,1,0,5,70,1.000000}
1
{default,0.000000,0.000000,450.000000,220.000000,0,2,0,4,0.000000,0,0,0,0}
0

0

3
{00000,boren,4,5,0.000000,0.000000,64,0}
1
{default,gongji3_texiao1,1,0,0,64,1.000000}
1
{default,470.000000,220.000000,470.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,1.200000,1.200000,1.200000,1.200000,0,0,1}
0

{1111,boren,2,5,0.000000,0.000000,64,0}
1
{default,gongji3_texiao2,1,0,0,64,1.000000}
1
{default,440.000000,110.000000,440.000000,110.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,0.990000,0.990000,0.990000,0.990000,0,0,1}
0

{22222,boren,1,5,0.000000,0.000000,64,0}
1
{default,gongji3_texiao2,1,0,0,64,1.000000}
1
{default,460.000000,330.000000,460.000000,330.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,0.990000,0.990000,0.990000,0.990000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",0.000000,0,4,0},{"gongji3",1.000000,5,70,1}}, 
pos_sequence      = {{0.000000,0.000000,450.000000,220.000000,0,2,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"boren",4,5,0.000000,0.000000,{{"gongji3_texiao1",1.000000,0,64,1}},{{470.000000,220.000000,470.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{{1.200000,1.200000,1.200000,1.200000,0,0,1}},{},0},
{"boren",2,5,0.000000,0.000000,{{"gongji3_texiao2",1.000000,0,64,1}},{{440.000000,110.000000,440.000000,110.000000,2,2,0,1,0.000000,0,0,0,0}},{{0.990000,0.990000,0.990000,0.990000,0,0,1}},{},0},
{"boren",1,5,0.000000,0.000000,{{"gongji3_texiao2",1.000000,0,64,1}},{{460.000000,330.000000,460.000000,330.000000,2,2,0,1,0.000000,0,0,0,0}},{{0.990000,0.990000,0.990000,0.990000,0,0,1}},{},0}}, 
bloodNum          = 2,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
