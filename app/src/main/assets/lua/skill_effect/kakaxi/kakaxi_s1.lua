--v3
--[[
kakaxi
1
0
penxue
1
{default,gongji3,1,0,0,111,1.000000}
1
{default,0.000000,0.000000,550.000000,220.000000,0,2,0,1,0.000000,0,0,0,0}
0

0

3
{gongji2t,kakaxi,2,0,0.000000,0.000000,111,0}
1
{default,gongji3_texiao,1,0,0,111,1.000000}
1
{default,470.000000,240.000000,470.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,1.200000,1.200000,1.200000,1.200000,0,0,1}
0

{gongji2t2,kakaxi,2,0,0.000000,0.000000,111,0}
1
{default,gongji3_texiao2,1,0,0,111,1.000000}
1
{default,480.000000,240.000000,480.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,1.200000,1.200000,1.200000,1.200000,0,0,1}
0

{gongji2t3_2,kakaxi,4,0,0.000000,0.000000,111,0}
1
{default,gongji3_texiao2_2,1,0,0,111,1.000000}
1
{default,480.000000,240.000000,480.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,1.200000,1.200000,1.200000,1.200000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji3",1.000000,0,111,1}}, 
pos_sequence      = {{0.000000,0.000000,550.000000,220.000000,0,2,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"kakaxi",2,0,0.000000,0.000000,{{"gongji3_texiao",1.000000,0,111,1}},{{470.000000,240.000000,470.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}},{{1.200000,1.200000,1.200000,1.200000,0,0,1}},{},0},
{"kakaxi",2,0,0.000000,0.000000,{{"gongji3_texiao2",1.000000,0,111,1}},{{480.000000,240.000000,480.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}},{{1.200000,1.200000,1.200000,1.200000,0,0,1}},{},0},
{"kakaxi",4,0,0.000000,0.000000,{{"gongji3_texiao2_2",1.000000,0,111,1}},{{480.000000,240.000000,480.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}},{{1.200000,1.200000,1.200000,1.200000,0,0,1}},{},0}}, 
bloodNum          = 3,
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
